<template>
  <div>
    <HeaderComp class="page" />

    <router-view />

    <FooterComp class="page" />
  </div>
</template>

<script>
import HeaderComp from './components/HeaderComponent.vue'
import FooterComp from './components/FooterComponent.vue'

export default {
  name: 'App',
  components: {
    HeaderComp,
    FooterComp
  }
}
</script>

<style lang="scss">
</style>
