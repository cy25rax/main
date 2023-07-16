<template>
    <div class="page data">
        <div class="left">
            <h1 class="left_title">404</h1>
            <p class="left_text">We are sorry, but the page you requested was not found</p>
            <router-link to="/home" class="left_button">
                <p class="left_button_text">Back To Home</p>
                <svg width="18" height="17" viewBox="0 0 18 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M1.85938 8.43542L15.5826 8.29857M10.4776 1.91138L17.0005 8.43436L10.3461 15.0887"
                        stroke="#CDA274" stroke-width="2" stroke-linecap="square" stroke-linejoin="round" />
                </svg>
            </router-link>
        </div>
        <div class="right">
            <img src="../assets/404.jpg" alt="">
        </div>
    </div>
</template>

<script>
export default {
    name: 'NotFoundComponent',

    data() {
        return {

        };
    },

    mounted() {

    },

    methods: {

    },
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

.data {
    display: flex;
    gap: 80px;
}

.left {
    display: flex;
    flex-direction: column;
    justify-content: center;

    &_title {
        color: #CDA274;
        font-size: 250px;
        font-style: normal;
        font-weight: 400;
        line-height: 125%;
        /* 312.5px */
    }

    &_text {
        color: #292F36;
        font-size: 35px;
        font-style: normal;
        font-weight: 400;
        line-height: 150%;
        /* 52.5px */
        letter-spacing: 0.35px;
    }

    &_button {
        display: flex;
        padding: 26px 54px;
        justify-content: center;
        align-items: center;
        gap: 10px;
        border-radius: 18px;
        background: #292F36;
        box-shadow: 0px 10px 20px 0px rgba(192, 192, 192, 0.35);

        &_text {
            color: #FFF;
            text-align: center;
            font-family: Jost;
            font-size: 18px;
            font-style: normal;
            font-weight: 600;
            line-height: 125%; /* 22.5px */
            letter-spacing: 0.36px;
        }
    }
}

.right {
    height: 1088px;
    flex-shrink: 0;

    & img {
        border-radius: 0px 0px 0px 360px;
    }

}</style>