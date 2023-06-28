<template>
    <div>
        <HeaderComp />

        <div class="banner page">
                <div class="banner_intro">
                    <h1 class="banner_intro_title">
                        Articles & News
                    </h1>
                    <p class="banner_intro_text">
                        Home / Blog
                    </p>
                </div>
        </div>

        <div class="content page">
                <h1 class="content_title">
                    Latest Post
                </h1>
                <div class="content_post">
                    <img class="content_post_img" src="img/post_photo.jpg" alt="">
                    <div class="content_post_info">
                        <h3 class="content_post_info_title">
                            Low Cost Latest Invented Interior Designing Ideas
                        </h3>
                        <p class="content_post_info_text">
                            Lorem ipsum dolor sit amet, adipiscing Aliquam eu sem vitae turpis dignissim maximus.posuere in.Contrary to popular belief.
                        </p>
                        <br>
                        <br>
                        <p class="content_post_info_text">
                            Lorem Ipsum is not simply random text. It has roots in a piece of classica.
                        </p>
                        <div class="content_post_info_inner">
                            <p class="content_post_info_text">
                                26 December,2022 
                            </p>
                            <svg width="70" height="70" viewBox="0 0 70 70" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <circle cx="35" cy="35" r="35" fill="#F4F0EC"/>
                                <path d="M32 44L40 35L32 26" stroke="#292F36" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                        </div>
                    </div>
                </div>
        </div>

        <div class="articles page">
                <h1 class="articles_title">Articles & News</h1>

                <div class="carts_example">
                    <div v-for="item in articles" :key="item.id" class="carts_example_cart">
                        <img class="carts_example_cart_img" :src="item.img" alt="">
                        <div class="carts_example_cart_inner">
                            <p class="carts_example_cart_inner_text">
                                {{item.short}}
                            </p>
                        </div>
                        <h2 class="carts_example_cart_title">
                            {{item.title}}
                        </h2>
                        <div class="carts_example_cart_date">
                            <p class="carts_example_cart_text">
                                {{item.date}} 
                            </p>
                            <a href="blog_details.html">
                                <svg class="carts_example_cart_svg" width="70" height="70" viewBox="0 0 70 70" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <circle cx="35" cy="35" r="35" fill="#F4F0EC"/>
                                    <path d="M32 44L40 35L32 26" stroke="#292F36" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                </svg>
                            </a>
                        </div>
                    </div>
                 </div>

                <div class="articles_pages">
                    <div class="articles_pages_page">01</div>
                    <div class="articles_pages_page">02</div>
                    <div class="articles_pages_page">03</div>
                    <div class="articles_pages_page"><img src="img/Vector.png" alt=""></div>

                </div>
        </div>

        <FooterComp />
    </div>
</template>
  
<script>
import HeaderComp from './components/HeaderComponent.vue'
import FooterComp from './components/FooterComponent.vue'
  
  export default {
    name: 'BlogApp',
    components: {
        HeaderComp,
        FooterComp
    },
    data() {
        return {
            articles: [
            {
                id: 1,
                img: 'img/example1.jpg',
                title: 'Let’s Get Solution For Building Construction Work',
                date: '26 December,2022',
                short: 'Kitchen Design'
            },
            {
                id: 2,
                img: 'img/example2.jpg',
                title: 'Low Cost Latest Invented Interior Designing  Ideas.',
                date: '22 December,2022',
                short: 'Living Design'
            },
            {
                id: 3,
                img: 'img/example3.jpg',
                title: 'Best For Any Office & Business Interior Solution',
                date: '25 December,2022',
                short: 'Interior Design'
            },
            {
                id: 4,
                img: 'img/example4.jpg',
                title: 'Let’s Get Solution For Building Construction Work',
                date: '26 December,2022',
                short: 'Kitchen Design'
            },
            {
                id: 5,
                img: 'img/example5.jpg',
                title: 'Low Cost Latest Invented Interior Designing Ideas.',
                date: '22 December,2022',
                short: 'Living Design'
            },
            {
                id: 6,
                img: 'img/example6.jpg',
                title: 'Best For Any Office & Business Interior Solution',
                date: '25 December,2022',
                short: 'Interior Design'
            }
        ]
        };
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
    background-image: url("../public/img/banner.jpg");
    background-size: no-repeat;
    background-position: center;
    background-size: cover;
    height: 356px;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;

    &_intro {
        max-width: 503px;
        background-color: #FFFFFF;
        border-radius: 37px 37px 0px 0px;
        display: flex;
        flex-direction: column;
        align-items: center;

        &_title {
            font-style: normal;
            font-weight: 400;
            font-size: 50px;
            line-height: 125%;
            color: #292F36;
            padding-top: 47px;
            padding-left: 78px;
            padding-right: 78px;
        }

        &_text {
            font-family: 'Jost';
            font-style: normal;
            font-weight: 400;
            font-size: 22px;
            line-height: 150%;
            letter-spacing: 0.01em;
            color: #4D5053;
            padding-bottom: 41px;
        }
    }
}

.content {
    padding-top: 200px;
    padding-bottom: 156px;

    &_title {
        font-style: normal;
        font-weight: 400;
        font-size: 50px;
        line-height: 125%;
        letter-spacing: 0.02em;
        color: #292F36;
        padding-bottom: 33px;
    }

    &_post {
        padding: 20px;
        border: 1px solid #E7E7E7;
        filter: drop-shadow(0px 10px 30px rgba(255, 255, 255, 0.25));
        border-radius: 62px;
        display: flex;

        &_img {
            border-radius: 62px;
        }

        &_info {
            display: flex;
            padding-left: 65px;
            padding-right: 75px;
            flex-direction: column;
            justify-content: center;

            &_title {
                font-style: normal;
                font-weight: 400;
                font-size: 25px;
                line-height: 125%;
                letter-spacing: 0.02em;
                color: #292F36;
                padding-bottom: 22px;
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

            &_inner {
                padding-top: 41px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
        }
    }


}

.articles {

    &_title {
        font-style: normal;
        font-weight: 400;
        font-size: 50px;
        line-height: 125%;
        letter-spacing: 0.02em;
        color: #292F36;
        padding-bottom: 30px;
    }

    &_pages {
        padding-top: 41px;
        padding-bottom: 200px;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 20px;

        &_page {
            width: 52px;
            height: 52px;
            border: 1px solid #CDA274;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;

            font-family: 'Jost';
            font-style: normal;
            font-weight: 500;
            font-size: 16px;
            line-height: 150%;
            text-transform: capitalize;
            color: #292F36;
        }

        & :first-child {
            background: #F4F0EC;
            border: none;
        }
    }
}

.carts_example {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    justify-content: space-between;
    gap: 20px;

    &_cart {
        padding: 20px;
        border: #E7E7E7 1px solid;
        border-radius: 62px;
        position: relative;

        &_inner {
                width: 124px;
                height: 41px;
                background: #FFFFFF;
                border-radius: 8px 8px 8px 0px;
                position: absolute;
                top: 250px;
                left: 40px;
                display: flex;
                justify-content: center;
                align-items: center;

                &_text {
                    font-family: 'Jost';
                    font-style: normal;
                    font-weight: 400;
                    font-size: 16px;
                    line-height: 150%;
                    letter-spacing: 0.01em;
                    text-transform: capitalize;
                    color: #4D5053;
                }
        }

        &_date {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-bottom: 42px;
        }

        &_img {
            border-radius: 62px 62px 0px 0px;
            width: 100%;
        }

        &_title {
            min-height: 93px;
            font-style: normal;
            font-weight: 400;
            font-size: 25px;
            line-height: 125%;
            letter-spacing: 0.02em;
            color: #292F36;

            padding-top: 21px;
            padding-bottom: 43px;
            max-width: 295px;
        }

        &_text {
            font-family: 'Jost';
            font-style: normal;
            font-weight: 400;
            font-size: 16px;
            line-height: 150%;
            letter-spacing: 0.01em;
            text-transform: capitalize;
            color: #4D5053;
        }
    }
}

.carts_example_cart:nth-child(2) {
    background-color: #E7E7E7;

    & circle{
        fill: #FFFFFF;
    }
}
</style>
  