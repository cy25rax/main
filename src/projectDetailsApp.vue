<template>
    <div>
        <div class="banner page">
        </div>

        <div class="page content">
            <div class="content_text">
                <h1 class="content_text_title">{{ data().title }}</h1>
                <p class="content_text_text">{{ data().text1 }}</p>
                <br>
                <p class="content_text_text">{{ data().text2 }}</p>
            </div>

            <CarouselComponent :img1="data().img1" :img2="data().img2" :img3="data().img3" />
        </div>
    </div>
</template>

<script>
import CarouselComponent from './components/CarouselComponent.vue';
import { mapState } from 'vuex';


export default {
    name: "ProjectDetailsApp",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {
        ...mapState(['data']),
    },
    components: {CarouselComponent }
};
</script>

<style lang="scss" scoped>
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
    background-image: url("../public/img/projectDetailsBanner.jpg");
    background-size: no-repeat;
    background-position: center;
    background-size: cover;
    height: 351px;
}

.content {
    display: flex;
    flex-direction: column;
    gap: 100px;
    padding-top: 200px;

    &_text {
        display: flex;
        flex-direction: column;
        max-width: 658px;
        margin: auto;

        &_title {
            color: #292F36;
            font-size: 50px;
            font-family: DM Serif Display;
            font-style: normal;
            font-weight: 400;
            line-height: 125%;
            letter-spacing: 1px;
        }

        &_text {
            color: #4D5053;
            font-size: 22px;
            font-family: Jost;
            font-style: normal;
            font-weight: 400;
            line-height: 150%;
            letter-spacing: 0.22px;
        }
    }

}
</style>