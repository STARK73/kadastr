var orderApi = Vue.resource('/orderapi{/id}')


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
        console.log(this.$router)
        orderApi.get().then(result =>
        result.json().then(data =>
        data.forEach(order => this.orders.push(order))
    )
    )
    }
});


var app = new Vue({
    el: '#app',
    template: '<orders-list :orders="orders" />',
    data: {
        orders: []
    }
})