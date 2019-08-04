using Nest;
using Newtonsoft.Json;
using ProdigyModels;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Linq;

namespace ProdigyBL
{
    public class SearchBL
    {
      
        public ElasticClient _elasticClient;
        public SearchBL()
        {
            _elasticClient = new ElasticClient(new Uri("http://165.22.221.253:9200/"));
        }
        public async Task<List<Product>> GetProducts(string searchTerm)
        {
            List<Product> product = new List<Product>();
            ProductAttribute attributes = new ProductAttribute();
            QueryContainerDescriptor<Product> queryContainerDescriptor;
            QueryContainer queryContainer = new QueryContainer();
            try
            {
                HttpClient httpClient = new HttpClient();
                var httpResponse  = await httpClient.GetAsync("http://134.209.145.199:5000/get-attributes?query_string="+ searchTerm);
                attributes = JsonConvert.DeserializeObject<ProductAttribute> (await httpResponse.Content.ReadAsStringAsync());
                foreach (var attribute in attributes.attributes)
                {
                    queryContainerDescriptor = new QueryContainerDescriptor<Product>();
                    switch (attribute.attribute_operator)
                    {
                        case "and":
                            queryContainerDescriptor.Wildcard(wc => wc.Field(new Field(attribute.attribute_type)).Value(attribute.attribute_value));
                            break;
                        case "less":
                            queryContainerDescriptor.Range(wc => wc.Field(new Field(attribute.attribute_type)).LessThanOrEquals(Convert.ToDouble(attribute.attribute_value)));
                            break;
                        case "greater":
                            queryContainerDescriptor.Range(wc => wc.Field(new Field(attribute.attribute_type)).GreaterThanOrEquals(Convert.ToDouble(attribute.attribute_value)));
                            break;

                    }
                    queryContainer = (queryContainerDescriptor || queryContainer);
                }
                queryContainerDescriptor = new QueryContainerDescriptor<Product>();
                queryContainerDescriptor.MultiMatch(m=>m.Query(searchTerm));
                product = (await _elasticClient.SearchAsync<Product>(x => x.Index("product_info").Query(q =>  queryContainer || queryContainerDescriptor).Size(100))).Documents.ToList();
             
            }
            catch (Exception ex)
            {

            }
            return product;
        }

        public async  Task<List<string>> GetQuerySuggestions(string searchTerm)
        {
            List<string> querySuggestion = new List<string>();
            try
            {
                searchTerm = searchTerm.ToLower();
               querySuggestion = (await _elasticClient.SearchAsync<Product>(x => x.Index("suggestion").Query(q =>  q.Wildcard(wc=>wc.Field(new Field("category")).Value(searchTerm)) ))).Documents?.Select(x=>x.category)?.ToList();
                if(querySuggestion==null || querySuggestion.Count == 0)
                {
                    querySuggestion = (await GetProducts(searchTerm)).Select(x=>x.title).Distinct().ToList();
                }
            }
            catch(Exception ex)
            {

            }
            return querySuggestion;
        }
    }
}
