<template>
    <div>{{ order.id }} {{ order.dateOrder }} {{ order.familyName }} {{ order.firstName.substring(0,1) }}.
        {{ order.patronymicName.substring(0,1) }}. {{ order.addressObject }} {{ order.status }}
    </div>
</template>

<script>
    export default {
        props: ['order']
    }
</script>

<style>

</style>