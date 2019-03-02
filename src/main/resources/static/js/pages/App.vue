<template>
    <div>
        <order-search :orders="orders"/>
        <orders-list :orders="orders"/>
    </div>
</template>

<script>
    import OrderSearch from 'components/order/OrderSearch.vue'
    import OrdersList from 'components/order/OrdersList.vue'

    export default {
        components: {
            OrderSearch,
            OrdersList
        },
        data() {
            return {
                orders: frontendData.orders,
                //orders: []
            }
        }
    }
</script>

<style>

</style>