<template>
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators content_carusel_indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active ">
                <img :src="require(`../assets/carousel/${img1}`)" class="content_carusel_img d-block w-100" alt="">
            </div>
            <div class="carousel-item ">
                <img :src="require(`../assets/carousel/${img2}`)" class="content_carusel_img d-block w-100" alt="">
            </div>
            <div class="carousel-item ">
                <img :src="require(`../assets/carousel/${img3}`)" class="d-block w-100 content_carusel_img" alt="">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CarouselComponent',
    props: {
        img1: String,
        img2: String,
        img3: String,
    },

};
</script>

<style lang="scss" scoped>
img {
    border-radius: 70px;
}
</style>