
function getIndex(list, id) {
    for(var i = 0; i < list.length; i++) {
        if(list[i].id == id) {
            return i;
        }
    }
    return -1;
}

var messageApi = Vue.resource('/api/order{/id}')

Vue.component('order-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            familyName: '',
            firstName: '',
            patronymicName: '',
            dateBirth: '',
            email: '',
            phoneMobile: '',
            phoneHome: '',
            phoneJob: '',
            pasSerial: '',
            pasNumber: '',
            pasDate: '',
            pasAddressBirth: '',
            pasIssued: '',
            snils: '',
            addressResidence: '',
            addressRegistration: '',
            addressRegistration: '',
            orders: [],
            message: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
    '<div>' +
        '<h3>1 Ваши персональные данные</h3>' +
        '<input type="text" placeholder="Фамилия" v-model="familyName"/><br>' +
        '<input type="text" placeholder="Имя" v-model="firstName"/><br>' +
        '<input type="text" placeholder="Отчество" v-model="patronymicName"/><br>' +
        '<input type="text" placeholder="Дата рождения" v-model="dateBirth"/><br>' +
        '<input type="text" placeholder="Email" v-model="email"/><br>' +
        '<input type="text" placeholder="Мобильный телефон" v-model="phoneMobile"/><br>' +
        '<input type="text" placeholder="Домашний телефон" v-model="phoneHome"/><br>' +
        '<input type="text" placeholder="Рабочий телефон" v-model="phoneJob"/><br>' +
        '<hr>' +

        '<h3>2 Ваши паспортные данные</h3>' +
        '<input type="text" placeholder="Серия" v-model="pasSerial"/><br>' +
        '<input type="text" placeholder="Номер" v-model="pasNumber"/><br>' +
        '<input type="text" placeholder="Дата выдачи" v-model="pasDate"/><br>' +
        '<input type="text" placeholder="Кем выдан" v-model="pasIssued"/><br>' +
        '<input type="text" placeholder="СНИЛС" v-model="snils"/><br>' +
        '<input type="text" placeholder="Место рождения" v-model="pasAddressBirth"/><br>' +
        '<input type="text" placeholder="Адрес прописки" v-model="addressRegistration"/><br>' +
        '<input type="text" placeholder="Адрес проживания" v-model="addressResidence"/><br>' +
        '<hr>' +

        '<h3>Выбор услуги</h3>' +
        '<input type="checkbox" id="mej" value="Межевание" v-model="orders">\n' +
        '<label for="mej">Межевание земельного участка</label><br>' +
        '<input type="checkbox" id="vynos" value="Вынос" v-model="orders">\n' +
        '<label for="vynos">Вынос характерных точек границ в натуру</label><br>' +

        '<input type="button" value="Отправить" @click="save" />' +
        '<p>{{ message }}</p>' +
    '</div>',
    methods: {
        save: function () {
            var order = {familyName: this.familyName, firstName: this.firstName, patronymicName: this.patronymicName,
                dateBirth: this.dateBirth, email: this.email, phoneMobile: this.phoneMobile, phoneHome: this.phoneHome,
                phoneJob: this.phoneJob,  pasSerialNumber: this.pasSerial + this.pasNumber, pasDate: this.pasDate,
                pasIssued: this.pasIssued, pasAddressBirth: this.pasAddressBirth, snils: this.snils,
                addressResidence: this.addressResidence, addressRegistration: this.addressRegistration};

                 messageApi.save({}, order).then(result =>
                    result.json().then(data => {
                        //this.messages.push(data);
                     message: data;
                    })
                )


        }
    }
});

Vue.component('message-row',{
    props: ['message', 'editMethod'],
    template: '<div>' +
        '<i>( {{ message.id }} )</i> {{ message.text }}' +
        '<span>' +
            '<input type="button" value="Edit" @click="edit" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div>' +
            '<order-form :messages="messages" :messageAttr="message" />' +
            '<message-row v-for="message in messages" :key="message.id" :message="message" :editMethod="editMethod" />' +
        '</div>',
    created: function() {
        messageApi.get().then(result =>
            result.json().then(data =>
                data.forEach(message => this.messages.push(message))
            )
        )
    },
    methods: {
        editMessage: function (message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
})