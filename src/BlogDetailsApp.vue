<template>
    <div>
        <HeaderComp/>

        <div class="banner page">
        </div>

        <div class="content page">
            <div class="content_left">
                <div v-for="item in getArticles" :key="item.id">
                    <BlogDetailsComponent :title="item.title" :img="item.img" :text1="item.text1" :text2="item.text2" :label="item.label" ></BlogDetailsComponent>
                </div>
            </div>

            <div class="content_right">
                <h1 class="content_right_title">Tags</h1>
                <div class="content_right_tags">
                    <div style="padding-bottom: 20px;"  v-for="item in tagNames" :key="item.id">
                        <input type="radio" name="*" :id="item">
                        <label @click="click(item)" class="content_right_button_text" :for="item">{{item}}</label>
                    </div>
                </div>
            </div>
        </div>

        <FooterComp />
    </div>
</template>
  
<script>
import BlogDetailsComponent from './components/BlogDetailsComponent.vue'
import HeaderComp from './components/HeaderComponent.vue'
import FooterComp from './components/FooterComponent.vue'
  
  export default {
    name: 'BlogDetails',
    components: {
      BlogDetailsComponent,
      HeaderComp,
      FooterComp
    },
    data() {
        return {
            filter: '',
        article: [
            {title: 'Tag 1 : Kitchen, Building',
            id: 1,
            img: 'img/article_img.jpg',
            text1: `Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpmaximus.posuere in.Contrary to popular belief.There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecthumour, or randomised words which don't look even slightly believable.`,
            text2: `Embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.`,
            label: [
                'Kitchen', 'Building'
            ]}, 
            {title: 'Tag 2: Kitchen, 1',
            id: 2,
            img: 'img/article_img2.jpg',
            text1: `Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpmaximus.posuere in.Contrary to popular belief.There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecthumour, or randomised words which don't look even slightly believable.`,
            label: [
                'Kitchen', '1'
            ]},
            {title: 'Tag 3: Building',
            id: 3,
            img: 'img/article_img.jpg',
            text1: `Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpmaximus.posuere in.Contrary to popular belief.There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecthumour, or randomised words which don't look even slightly believable.`,
            text2: `Embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.`,
            label: [
                'Building'
            ]},
            {title: 'Tag 4: Kitchen Planning',
            id: 4,
            img: 'img/article_img3.jpg',
            text1: `Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpmaximus.posuere in.Contrary to popular belief.There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecthumour, or randomised words which don't look even slightly believable.`,
            text2: `Embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.`,
            label: [
                'Kitchen Planning'
            ]},
            {title: 'Tag 5: Architecture, Kitchen Planning',
            id: 5,
            img: 'img/article_img.jpg',
            text1: `Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpmaximus.posuere in.Contrary to popular belief.There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecthumour, or randomised words which don't look even slightly believable.`,
            text2: `Embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.`,
            label: [
                'Architecture', 'Kitchen Planning'
            ]},
            ]
        };
    },

    methods: {
        // метод который меняет фильтр для сортировки статей
        click(itm) {
            if (this.filter === itm) {
                this. filter = ''
            } else {
                this.filter = itm;
            }
        },
    },
    computed: {
        // создает массив из  уникальных ТЕГОВ которые храгятся в статьях 
        // из которых создаются кнопки для сортировки
        tagNames() { 
            const tempArticles = [];
            for (let i = 0; i < this.article.length; i++) {
                for (let j = 0; j < this.article[i].label.length; j++) {
                    tempArticles.push(this.article[i].label[j])
                }
            }
            const tempArticlesUnique = [... new Set(tempArticles)]
            return tempArticlesUnique;
        },
        getArticles() { // выводит статьи по заданаму фильтру
            if (this.filter === '') {
                return this.article
            } else {
                const tempArticles = [];
                this.article.forEach(el => {
                    el.label.forEach(elt => {
                        if (elt === this.filter) {
                            tempArticles.push(el);
                        }
                    });
                });
                return tempArticles
            }
        }
    },
  }
</script>
  
<style lang="scss">
    * {
        margin: 0;
        padding: 0;
        font-family: 'DM Serif Display', serif;
    }

    a {
        text-decoration: none;
    }

    img {
        max-width: 100%;
        max-height: 100%;
    }

    .page {
        padding-left: calc(50% - 1200px / 2);
        padding-right: calc(50% - 1200px / 2);
    }


    .banner {
        background-image: url("../public/img/blog_solo_banner.jpg");
        background-size: no-repeat;
        background-position: center;
        background-size: cover;
        height: 351px;
    }

    input[type=radio]:checked + label {
        background: #292F36;
        color: #FFFFFF;
    }

    .content {
    display: flex;
    gap: 57px;
    padding-top: 200px;

    &_left {
        width: 798px;
        display: flex;
        flex-direction: column;
        gap: 50px;
    }

    &_right {
        width: 345px;

        &_tags {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;

            & input {
                display: none;
            }
        }

        &_title {
            font-style: normal;
            font-weight: 400;
            font-size: 25px;
            line-height: 125%;
            letter-spacing: 0.02em;
            color: #292F36;
            padding-bottom: 24px;
        }

        &_button {

            &_text {
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
            }
        }
    }
    }
</style>
  