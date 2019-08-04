<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
     <img src="https://blogs.mulesoft.com/wp-content/uploads/2017/08/dataweave.png">
    <SearchInput :queries-data="queries" @text-input="suggestQueries" @search="searchProduct" />
    <!-- <ProductCard :header="'TShirt'" :sub-heading="'brand'" :details="'More Info'"/> -->
  </div>
</template>

<script>
import SearchService from '../services/search.service';
export default {
  name: 'home',
  data(){
    return{
      queries:[]
    }
  },
  methods:{
    suggestQueries: async function(searchTerm){
      this.queries = await SearchService.getQuerySuggestions(searchTerm);   
    },
    searchProduct: function(searchTerm){
      this.$router.push({ path: 'product', query: { query: searchTerm } });
    }

  }
}
</script>
