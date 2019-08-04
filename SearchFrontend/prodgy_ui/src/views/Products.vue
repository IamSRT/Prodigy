<template>
<div>
      <!-- <SearchInput :queries-data="queries" @text-input="suggestQueries" @search="searchProduct" /> -->
      <ProductCard v-for="(product,index) in products" :key="index" :header="product.title" :sub-heading="product.brand" :rating="product.rating" :price="product.price" :url="product.url"></ProductCard>

</div>
</template>
<script>
import SearchService from '../services/search.service';
export default {
    name:"Products",
    data(){
        return{
            products:[],
            queries:[],            
        }
    },
 async mounted(){
      this.products = await this.searchProduct(this.$route.query.query);
      console.log(this.products);
    },
  methods:{
    suggestQueries: async function(searchTerm){
      this.queries = await SearchService.getQuerySuggestions(searchTerm);  
    },
    searchProduct: async function(searchTerm){        
      return await SearchService.getSearchData(searchTerm);
    }
  }

}
</script>
<style>

</style>

