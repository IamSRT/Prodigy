<template>
<div class="prodigy-autocomplete">
    <form @submit.prevent="onEnter">
   <md-autocomplete v-model="selectedQuery" :md-options="queries" :md-fuzzy-search="false" @md-changed="onInputChange" @md-selected="onInputSelected" >
      <label>Search </label>

      <template slot="md-autocomplete-item" slot-scope="{ item, term }">
        <md-highlight-text :md-term="term">{{ item }}</md-highlight-text>
      </template>

    </md-autocomplete>
    </form>
</div>
</template>
<script>
export default {
    name:"SearchInput",
    props:{
     queriesData:Array
    },
    data(){
        return{
        selectedQuery:'',
        queries : []
        }
    },
    mounted(){
        console.log(this.queriesData);
       this.queries = this.queriesData;
    },
    methods:{
      onInputChange: function(searchterm){
          this.$emit('text-input',searchterm);
      },
      onInputSelected: function(selectedValue){
         if(selectedValue)
          this.$emit('search',selectedValue);
      },
      onEnter:function(){
          if(this.selectedQuery)
           this.$emit('search',this.selectedQuery);
      }
    },
    updated(){
         this.queries = this.queriesData;
    }

}
</script>
<style lang="scss" scoped>
  .md-autocomplete  {    
    margin-top: 36px;
    display: block;
  }
  .prodigy-autocomplete{
      width:50%;
      position: absolute;
      top:40%;
      left:25%;
  }
</style>