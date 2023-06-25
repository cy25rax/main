<template>
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
</template>
  
<script>
  
  export default {
    name: 'BlogApp',
    components: {
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
</style>
  