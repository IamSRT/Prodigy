using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ProdigyBL;
using ProdigyModels;

namespace ProdigyAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class SearchController : ControllerBase
    {
        public SearchBL _searchBL;

        public SearchController()
        {
            _searchBL = new SearchBL();
        }
        [HttpGet]
        public async Task<List<string>> GetSuggestions(string searchTerm)
        {
            return await _searchBL.GetQuerySuggestions(searchTerm);
        }

        [HttpGet]
        public async Task<List<Product>> GetProducts(string searchTerm)
        {
            return await _searchBL.GetProducts(searchTerm);
        }
    }
}