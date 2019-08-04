from service.text_basic_analyzer import get_colors, get_adjectives,get_sentences,get_money, get_continuous_chunks,get_words, get_stem, check_less, get_tokens,check_greater
from service.elastic_search_client import push_to_product_info_index, push_to_suggestion_category_index

_pure = ["color", "price", "size", "url", "brand", "rating", "id", "gender"]
_partial = ["title", "name", "description", "meta", "features"]

class Attribute:
    def __init__(self, attribute_type, attribute_value, attribute_operator):
        self.attribute_type = attribute_type
        self.attribute_value = attribute_value
        self.attribute_operator = attribute_operator
        
def push_attributes(data):
    attributes = {}
    category = {}
    nouns = []
    adjs = []
    print(data)
    if "category"  in data:
        category["category"] = data["category"]
    
    if "subcategory" in data:
        if "category"  in data:
            category["category"] = category["category"]+" "+ data["subcategory"]
        else:
            category["category"] = data["subcategory"]
    
    if "id" in data:
        category["id"] = data["id"]
            
    for field in data:
        if field == "price":
            attributes[field] = get_money(data[field])
            continue
        
        if field in _pure:
            attributes[field] = data[field]
            continue
        
        sents = get_sentences(data[field])
        
        if field in _partial:
            attributes[field] = data[field]
            nouns.extend(get_nouns(sents))
            adjs.extend(get_adjectives(sents))
            
        if field == "meta" or field == "features":
            attributes[field] = get_tokens(data[field])
            continue
        
        colors = get_colors(sents)
        for color in colors:
            attributes["color"] = color
            break
    
    if "category"  in category:
        attributes["category"] = category["category"]
     
    if len(nouns) >0:
        attributes["nouns"] = nouns
    if len(adjs) > 0:
        attributes["adjectives"] = adjs
        
    print(attributes)
    print(category)
    
    push_to_product_info_index(attributes)
    if "category"  in category:
        push_to_suggestion_category_index(category)

def get_attributes(data):
    attributes = []
    sents = get_sentences(data)
    price = get_price(sents)
    if price != None:
        attributes.append(price)
        
    colors = get_colors(sents)
    for color in colors:
        a = Attribute("color", color, "and")
        attributes.append(a.__dict__)
    adjs = get_adjectives(sents)
    if len(adjs) > 0:
        a = Attribute("adjectives", adjs[0], "and")
        attributes.append(a.__dict__)
        
    nouns = get_nouns(sents)
    if len(nouns) > 0:
        a = Attribute("nouns", nouns[0], "and")
        attributes.append(a.__dict__)
        
    return attributes

def get_price(sents):
    words = get_stem(get_words(sents))
    operator = ""
    i = 0
    print(words)
    for word in words:
        if check_less(word):
            operator = "less"
            break
        if check_greater(word):
            operator = "greater"
            break
        i = i+1
    print(i)
    
    if i < len(words)-1 and operator != "" and get_money(words[i+1]) != "":
        price = get_money(sents)
        if price != "-1":
            a = Attribute("price", price, operator)
        return a.__dict__
    return None

def get_nouns(sents):
    nouns = []
    for sent in sents:
        nouns.extend(get_continuous_chunks(sent))
    return nouns