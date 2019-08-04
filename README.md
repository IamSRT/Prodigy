# Prodigy
Product Discovery and Ontology Engine

The product has 5 components
1. Frontend web application
2. Search Backend Application
3. Tag Attribution Engine
4. Data Extraction Engine
5. Data Storage Engine

The prodigy application can be accessed from following url:
http://134.209.145.199:8080/prodgy/#/

----- Tag Attribution Engine Set up
Pre-requisities:
1. Python3
2. Complete NLTK download
3. Flask micro web application framework

Steps to run:
1. Cd to tag_attribution_engine folder
2. initialize virtual environment
3. install flask and nltk 

----- Frontend web application

Pre-requisites :
1. npm
2. yarn
3. vue-cli

Steps to run:
1. open terminal and navigate to folder prodgy_ui
2. yarn install
3. yarn serve

host url : http://134.209.145.199:8080/prodgy/

----- Search Backend Application (.Net Core)
.Net core version : V2.1

Running application using visual studio in windows 
https://docs.microsoft.com/en-us/dotnet/core/windows-prerequisites?tabs=netcore2x
https://docs.microsoft.com/en-us/dotnet/core/tools/dotnet-run?tabs=netcore21

Running application using other os
https://docs.microsoft.com/en-us/dotnet/core/tutorials/using-with-xplat-cli

API Links:
http://134.209.151.250/api/search/GetProducts?searchTerm={{search_string}}
http://134.209.151.250/api/search/GetSuggestions?searchTerm={{search_string}}







