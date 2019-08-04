from nltk.tokenize import sent_tokenize, RegexpTokenizer
from nltk import pos_tag, ne_chunk_sents, ne_chunk, word_tokenize
from nltk.corpus import stopwords
from nltk.tree import Tree
from nltk.stem import 	WordNetLemmatizer
from nltk.stem.porter import PorterStemmer
import re


_adj_tags = ["JJ", "JJR", "JJS"]


_colors = ['alice blue', 'antique white', 'aqua', 'aquamarine', 'azure', 'beige', 'bisque', 'black', 'blanched almond', 'blue', 'blue violet', 'brown', 'burly wood', 'cadet blue', 'chartreuse', 'chocolate', 'coral', 'cornflower blue', 'cornsilk', 'crimson', 'cyan', 'dark blue', 'dark cyan', 'dark golden rod', 'dark gray', 'dark grey', 'dark green', 'dark khaki', 'dark magenta', 'dark olive green', 'dark orange', 'dark orchid', 'dark red', 'dark salmon', 'dark sea green', 'dark slate blue', 'dark slate gray', 'dark slate grey', 'dark turquoise', 'dark violet', 'deep pink', 'deep sky blue', 'dim gray', 'dim grey', 'dodger blue', 'fire brick', 'floral white', 'forest green', 'fuchsia', 'gainsboro', 'ghost white', 'gold', 'golden rod', 'gray', 'grey', 'green', 'green yellow', 'honey dew', 'hot pink', 'indian red', 'indigo', 'ivory', 'khaki', 'lavender', 'lavender blush', 'lawn green', 'lemon chiffon', 'light blue', 'light coral', 'light cyan', 'light golden rod yellow', 'light gray', 'light grey', 'light green', 'light pink', 'light salmon', 'light sea green', 'light sky blue', 'light slate gray', 'light slate grey', 'light steel blue', 'light yellow', 'lime', 'lime green', 'linen', 'magenta', 'maroon', 'medium aqua marine', 'medium blue', 'medium orchid', 'medium purple', 'medium sea green', 'medium slate blue', 'medium spring green', 'medium turquoise', 'medium violet red', 'midnight blue', 'mint cream', 'misty rose', 'moccasin', 'navajo white', 'navy', 'old lace', 'olive', 'olive drab', 'orange', 'orange red', 'orchid', 'pale golden rod', 'pale green', 'pale turquoise', 'pale violet red', 'papaya whip', 'peach puff', 'peru', 'pink', 'plum', 'powder blue', 'purple', 'rebecca purple', 'red', 'rosy brown', 'royal blue', 'saddle brown', 'salmon', 'sandy brown', 'sea green', 'sea shell', 'sienna', 'silver', 'sky blue', 'slate blue', 'slate gray', 'slate grey', 'snow', 'spring green', 'steel blue', 'tan', 'teal', 'thistle', 'tomato', 'turquoise', 'violet', 'wheat', 'white', 'white smoke', 'yellow', 'yellow green']
colors = { i : True for i in _colors }
def check_color(word):
    return word in colors

_less = ['under', 'less', 'le','lesser','lessthan', 'below', 'low', 'lower', 'lowerthan']
less = { i : True for i in _less }
def check_less(word):
    return word in less

_greater = ['greater', 'above', 'abov', 'highthan','more', 'morethan', 'high', 'higher', 'higherthan', 'greater', 'greaterthan']
greater = { i : True for i in _greater }
def check_greater(word):
    return word in greater

def get_stop_words():
    return stopwords.words('english')

def remove_stop_words(words):
    final = []
    s_words = get_stop_words()
    for w in words:
        if w in s_words:
            continue
        final.append(w)
    return final
        

def get_tokens(data):
    sents = get_sentences(data)
    words = get_words(sents)
    words = remove_stop_words(words)
    return get_stem(words)
    
def get_sentences(data):
    sent_tokens = sent_tokenize(data)
    return sent_tokens

def get_words(sents):
    tokenizer = RegexpTokenizer('[a-zA-Z0-9]\w+\'?\w*')
    tokens = []
    for sent in sents:
        tokens.extend(list(tokenizer.tokenize(sent)))
    tokens = [ token.lower() for token in tokens ]
    return tokens

def get_lemma(words):
    wordnet_lemmatizer = WordNetLemmatizer()
    lemme =[]
    for word in words:
        lemme.append(wordnet_lemmatizer.lemmatize(word))
    return lemme

def get_stem(words):
    porter_stemmer  = PorterStemmer()
    stems =[]
    for word in words:
        stems.append(porter_stemmer.stem(word))
    return stems
        
def get_pos_tags(sents):
    tagged_sentences = [pos_tag(sent) for sent in sents]
    return tagged_sentences

def get_chunks(sents):
    chunked_sentences = ne_chunk_sents(sents, binary=True)
    return chunked_sentences

def extract_entity_names(t):
    entity_names = []
    
    if hasattr(t, 'node') and t.node:
        if t.node == 'NE':
            entity_names.append(' '.join([child[0] for child in t]))
        else:
            for child in t:
                entity_names.extend(extract_entity_names(child))
                
    return entity_names

def get_all_named_entities(chunks):
    entity_names = []
    for tree in chunks:
        # Print results per sentence
        # print extract_entity_names(tree)
        entity_names.extend(extract_entity_names(tree))
    return entity_names

def get_continuous_chunks(text):
    chunked = ne_chunk(pos_tag(word_tokenize(text)))
    continuous_chunk = []
    current_chunk = []
    for i in chunked:
        if type(i) == Tree:
            current_chunk.append(" ".join([token for token, pos in i.leaves()]))
        elif current_chunk:
            named_entity = " ".join(current_chunk)
            if named_entity not in continuous_chunk:
                continuous_chunk.append(named_entity)
                current_chunk = []
        else:
            continue
    return continuous_chunk

def get_money(sents):
    xp = '(?:[,\d]+.?\d*)'
    for sent in sents:
        res = re.findall(xp, sent)
        if res != [] and res != None:
            return res[0]
    return "-1"

def filter_text(word):
    xp = '[a-zA-Z]*'
    return re.findall(xp, word)[0]

def get_colors(sents):
    colors = []
    tokens = get_words(sents)
    for token in tokens:
        if check_color(token):
            colors.append(token)
    return colors

def get_adjectives(sents):
    adjs = []
    for sent in sents:
        pos_tagged = pos_tag(word_tokenize(sent))
        for tag in pos_tagged:
            if tag and tag[1] in _adj_tags:
                adjs.append(tag[0])
    return adjs
    