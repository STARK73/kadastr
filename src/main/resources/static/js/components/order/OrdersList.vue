<template>
    <div>
        <order-row v-for="order in orders" :order="order" :key="order.id"></order-row>
    </div>
</template>

<script>
    import OrderRow from 'components/order/OrderRow.vue'

    export default {
        components: {
            OrderRow
        },
        props: ['orders'],
        /*created: function () {
            this.$resource('/orderapi').get().then(result =>
                result.json().then(data =>
                    data.forEach(order => this.orders.push(order))
                )
            )
        }*/
    }
</script>

<style>

</style>