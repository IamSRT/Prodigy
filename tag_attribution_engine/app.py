from flask import Flask, request
from service.attribute_builder import get_attributes, push_attributes
app = Flask(__name__)


@app.route('/health')
def health_check():
    return 'True'


@app.route('/get-attributes')
def get_text_attributes():
    #try:
        query_string = request.args.get("query_string")
        attributes = get_attributes(query_string)
        response = {}
        response["attributes"] = attributes
        return response
    #except:
        #print("An exception occured" + str(request.json))
        #return {"status":"failed", "attributes":[]}

@app.route('/push-attributes', methods = ['POST'])
def push_text_attributes():
    #try:
        data = request.json
        push_attributes(data)
        response = {"status":"success"}
        return response
    #except:
        #print("An exception occured" + str(request.json))
        #return {"status":"failed"}

@app.errorhandler(404)
def page_not_found(e):
    return {"status": "404", "message":"Invalid Route", }


if __name__ == '__main__':
    app.run(host='0.0.0.0',port=5000)