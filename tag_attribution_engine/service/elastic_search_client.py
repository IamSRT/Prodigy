from elasticsearch import Elasticsearch

ES_HOST = {"host": "165.22.221.253", "port": 9200}
es = Elasticsearch(hosts=[ES_HOST])

def push_to_suggestion_category_index(data):
    resp = es.index(index="suggestion", doc_type="category", id = data["id"],body=data)
    print(resp)
    return resp["result"] == "created"

def push_to_suggestion_subcategory_index(data):
    resp = es.index(index="suggestion", doc_type="subcategory", id = data["id"], body=data)
    print(resp)
    return resp["result"] == "created"

def push_to_product_info_index(data):
    resp = es.index(index="product_info", doc_type="product_details", id = data["id"],body=data)
    print(resp)
    return resp["result"] == "created"