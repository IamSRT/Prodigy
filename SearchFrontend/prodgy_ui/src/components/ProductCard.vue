<template>
    <md-card class="md-primary" md-theme="purple-card" md-with-hover>
      
        <md-card-header>
          <div class="md-title"><a target="_default" :href="url">{{header}}</a></div>
          <div class="md-subhead">{{subHeading}}</div>
        </md-card-header>

        <md-card-content>
         <div><div class="description">Rating: {{rating}}</div><div class="price">Price: {{price}}</div></div>         
        </md-card-content>
    </md-card>
</template>
<script>
export default {
    name:"ProductCard",
    props:{
        header  :String,
        subHeading: String,
        rating :String,
        price:String,
        url:String
    }

}
</script>
<style scoped>
.md-card{
    width: 80%;
    left: 10%;
    margin-top: 12px;
    text-align: left;
}
.price{
    text-align: right;
    display: inline-block;
    width:20%;  
    font-weight: bold;
    font-size: 13px;
}
.description{
    display: inline-block;
    width: 40%
}
.md-card .md-title{
    font-size: 21px;
}
.md-card .md-subhead{
    font-size: 13px;
}
</style>

