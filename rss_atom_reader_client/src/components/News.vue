<template>
  <div class="news">
    <div class="searchComponent" id="findByCategory">
      <input v-model="filtered" type="search" class="searchInput" placeholder="Find by category">
    </div>
    <div class="row">
      <div v-for="item in filteredNews" :key="item.Id" class="cardComponent col-sm-6 col-md-4 col-lg-3">
        <div class="card">
          <img :src="item.imageUrl" class="cardImage" alt="item-imageUrl">
          <div class="card-body">
            <h5 class="card-title">{{item.title}}</h5>
            <p class="card-text">{{item.description}}</p>
          </div>
          <div class="card-footer">{{item.publishDate}}<a :href="item.link" class="card-link btn btn-dark">Read more</a></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "news",

  data() {
    return {
      News: [],
      filtered: ''
    };
  },

  methods: {
    fetchNews() {
      this.$http
        .get("http://localhost:8085/api/news")
        .then(response => response.json())
        .then(result => (this.News = result));
    }
  },

  computed: {
    filteredNews() {
      const search = this.filtered.toLowerCase().trim();

      if(!search) return this.News;

      return this.News.filter(
        c=>c.category.toLowerCase().indexOf(search) > -1
      );
    }
  },

  created: function() {
    this.fetchNews();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.news {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.searchComponent {
  margin: 20px 0px 40px 0px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.searchInput {
  border: none;
  background: none;
  text-decoration: none;
  border-bottom: 2px solid gray;
  width: 300px;
  height: 30px;
}

.searchInput:focus {
  outline: none;
  border-bottom: 2px solid black;
}

.row {
  margin:0;
  padding:0;
  
}

.cardComponent {
  margin-bottom: 50px;
}

.cardImage {
  width: 100%;
  height: 250px;
}

.card-link {
  background: rgba(0, 0, 0, 0.2);
  
}

.card {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.1);
  border: 2px solid lightgray;
}

.card:hover{
  background-color: rgba(0, 0, 0, 0.2);
}

.card-link {
  text-align: center;

}

.card-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: rgba(0, 0, 0, 0.1);
  
}

</style>
