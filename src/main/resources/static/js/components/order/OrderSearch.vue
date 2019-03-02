<template>
    <div>
        <input type="text" placeholder="Фамилия" v-model="familyName"/>
        <input type="text" placeholder="Адрес объекта" v-model="addressObject"/>
        <input type="button" value="Поиск" @click="find"/>
    </div>
</template>

<script>
    export default {
        props: ['orders'],
        data: function () {
            return {
                familyName: '',
                addressObject: ''
            }
        },
        methods: {
            find: function () {
                var find = {familyName: this.familyName, addressObject: this.addressObject};
                this.orders.length = 0
                this.$resource('/orderapi/search').save({}, find).then(result =>
                    result.json().then(data => {
                        data.forEach(order => this.orders.push(order))
                    })
                )
            }
        }
    }
</script>

<style>

</style>