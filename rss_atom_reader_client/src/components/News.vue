<template>
  <div class="news">

  </div>
</template>

<script>
export default {
  name: 'news',

  methods: {
    fetchNews() {
      this.$http
        .get('http://localhost:8085/api/news')
        .then(response => response.json())
        .then(result => console.log(result));
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
