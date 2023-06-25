const { defineConfig } = require('@vue/cli-service')

module.exports = {
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
    },
    blog_details: {
      entry: 'src/blogDetails.js',
      template: 'public/blog_details.html',
    },
    blog: {
      entry: 'src/blog.js',
      template: 'public/blog.html',
    }
  }
}
