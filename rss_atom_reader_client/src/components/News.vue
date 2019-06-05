<template>
  <div class="news">
    <div class="container" id="findByCategory">
      <input v-model="filtered" type="search" class="form-control" placeholder="Find by category">
    </div>
    <div class="row">
      <div v-for="item in filteredNews" :key="item.Id" class="col-sm-6 col-md-4 col-lg-3">
        <div class="card">
          <img :src="item.imageUrl" class="card-img-top" alt="item-imageUrl">
          <div class="card-body">
            <h5 class="card-title">{{item.title}}</h5>
            <p class="card-text">{{item.description}}</p>
            <a :href="item.link" class="btn btn-primary">Read more</a>
          </div>
          <div class="card-footer">{{item.publishDate}}</div>
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
</style>
