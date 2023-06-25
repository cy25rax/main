<template>
    <div class="content_left_article">
        <h1 class="content_left_article_title">
            {{title}}
        </h1>
        <img class="content_left_article_img" :src="img" alt="">
        <p class="content_left_article_text">
            {{text1}}
        </p>
        <p class="content_left_article_text">
            {{text2}}
        </p>
        <p class="content_left_button">{{label}}</p>
    </div>
</template>
  
<script>
  export default {
    name: 'BlogDetailsComponent',
    props: {
      title: {
        type: String
      },
      img: {
        type: String
      },
      text1: {
        type: String
      },
      text2: {
        type: String
      },
      label:{
        type: Array
      }
    }
  }
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .content {
    &_left {
        &_button {
            font-family: 'Jost';
            font-style: normal;
            font-weight: 400;
            font-size: 18px;
            line-height: 125%;
            letter-spacing: 0.02em;
            color: #292F36;

            padding: 9px 30px;
            background: #F4F0EC;
            border-radius: 10px;
            border: none;
            display: inline;
        }

        &_article {
            display: flex;
            flex-direction: column;
            gap: 11px;

            &_title {
                font-style: normal;
                font-weight: 400;
                font-size: 50px;
                line-height: 125%;
                letter-spacing: 0.02em;
                color: #292F36;
            }

            &_img {
                border-radius: 50px;
            }

            &_text {
                font-family: 'Jost';
                font-style: normal;
                font-weight: 400;
                font-size: 22px;
                line-height: 150%;
                letter-spacing: 0.01em;
                color: #4D5053;
            }
        }
    }
}
</style>
  