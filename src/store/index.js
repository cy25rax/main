import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        // начальное состояние
        data: {
            title: 'Minimal Look Bedrooms',
            text1: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquamsem vitae turpis dignissim maximus. Aliquam sollicitudin tellumassa, vbel maximus purus posuere in. Dojrices gravida dignissim. Praesent at nibh in mi fringilla mattis. Phasellus ut dolor odio. Aenean in the ipsum vel lectus bibendum commodo.',
            text2: 'In nec sem suscipit, convallis leo vitae, lacinia nibh. Cras amet tellus lectus. Vivamus ipsum nunc, mattis quis nibh id, pellentesque arcu. Donec a pellentesque Cras erat enim, gravida non ante vitae,elequis convallis elit, in viverra felis. Donec ultrices tellus vitae iaculisvd porta. Proin tincidunt ligula id purus porttitor.',
            img1: 'article_img.jpg',
            img2: 'article_img2.jpg',
            img3: 'article_img3.jpg'
        }
    },
    actions: {
        // методы для асинхронных операций
        fetchData({ commit }) {
            setTimeout(() => {
            const paymentsList = [
                {date: '28.03.2020'},
                {date: '24.03.2020'},
                {date: '24.03.2020'}
            ];
            commit('setData', paymentsList);
            }, 1000);
        }
            
    },
    getters: {
        getCounter: state => state.count,
        getData: state => state.dataArrray
        // методы для чтения состояния
    },
})
