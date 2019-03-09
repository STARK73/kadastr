var orderApi = Vue.resource('/api/order')
var orderApiSearch = Vue.resource('/api/order/search')
Vue.component('order-find', {
    props: ['orders'],
    data: function () {
        return {
            familyName: '',
            addressObject: ''
        }
    },
    template: '<div> ' +
        '<input type="text" placeholder="Фамилия" v-model="familyName"/>' +
        '<input type="text" placeholder="Адрес объекта" v-model="addressObject"/>' +
        '<input type="button" value="Поиск" @click="find" />' +
        '</div>',
    methods: {
        find: function () {
            var find = {familyName: this.familyName, addressObject: this.addressObject};
            this.orders.length = 0;
            orderApiSearch.save({}, find).then(result =>
            result.json().then(data => {
                data.forEach(order => this.orders.push(order))
        })
        )
        }
    }
});

Vue.component('order-row', {
    props: ['order'],
    template: '<div>{{ order.id }} {{ order.dateOrder }} {{ order.familyName }} {{ order.firstName.substring(0,1) }}.' +
        '{{ order.patronymicName.substring(0,1) }}. {{ order.addressObject }} {{ order.status }} </div>'
});

Vue.component('orders-list', {
    props: ['orders'],
    template:
        '<div><order-row v-for="order in orders" :order="order" :key="order.id"></order-row></div>',
    created: function () {
        orderApi.get().then(result =>
            result.json().then(data =>
                data.forEach(order => this.orders.push(order))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<div><order-find :orders="orders" />' +
              '<orders-list :orders="orders" /></div>',
    data: {
        orders: []
    }
})