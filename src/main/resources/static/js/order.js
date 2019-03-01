var messageApi = Vue.resource('/orderapi')



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
            pasIssued: '',
            snils: '',
            addressResidence: '',
            addressRegistration: '',
            orderMej: '',
            orderTexPlan: '',
            orderSoglOpr: '',
            mejOpKv: '',
            mejOpSo: '',
            addressObject: '',
            typeProperty: '',
            targetPlacing: '',
            entitlingType: '',
            entitlingNum: '',
            entitlingDate: '',
            orderVun: '',
            ansver: '',
            sum: 0,
            isSogl: false,
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
        '<input type="text" placeholder="Адрес прописки" v-model="addressRegistration"/><br>' +
        '<input type="text" placeholder="Адрес проживания" v-model="addressResidence"/><br>' +
        '<hr>' +

        '<h3>3 Выбор услуги</h3>' +

        '<input type="checkbox" id="mej" v-model="orderMej" @change="sumOrd">' +
        '<label for="mej">Межевание земельного участка под ИЖС и ЛПХ с подготовкой межевого плана</label><br>' +

            '<div v-if="orderMej">' +
                '<select  v-model="mejOpKv" @change="sumOrd">' +
                    '<option disabled value="">Скольки квартирный дом</option>' +
                        '<option v-bind:value="{ id: 1, price: 100}">1 квартирный</option>' +
                        '<option v-bind:value="{ id: 2, price: 200}">2 квартирный</option>' +
                        '<option v-bind:value="{ id: 3, price: 300}">3 квартирный</option>' +
                '</select><br>' +

                '<select v-model="typeProperty">' +
                '<option disabled value="">Выберите тип собственности</option>' +
                    '<option>cобственности</option>' +
                    '<option>владении</option>' +
                    '<option>пользовании</option>' +
                '</select><br>' +

                '<textarea type="text" placeholder="Адрес объекта" v-model="addressObject"></textarea><br>' +
                '<input type="text" placeholder="Правоустанавливающий документ" v-model="entitlingType"/><br>' +
                '<input type="text" placeholder="Номер документа" v-model="entitlingNum"/><br>' +
                '<input type="text" placeholder="Дата документа" v-model="entitlingDate"/><br>' +
            '</div>'+

        '<input type="checkbox" id="vyn" v-model="orderVun" @change="sumOrd">' +
        '<label for="vyn">Подготовка схемы расположения земельного участка на КПТ</label><br>' +

        '<input type="checkbox" id="texPlan" v-model="orderTexPlan" @change="sumOrd">' +
        '<label for="texPlan">Подготовка технического плана здания</label><br>' +

        '<input type="checkbox" id="SoglOpr" v-model="orderSoglOpr" @change="sumOrd">' +
        '<label for="SoglOpr">Составление соглашения об определении порядка пользования земельного участка</label><br><hr>' +



        '<div v-if="orderMej">' +
            '<p>Межевание земельного участка под ИЖС и ЛПХ с подготовкой межевого плана: {{ mejOpKv.price }} руб.</p><br>' +
        '</div>'+
        '<div v-if="orderVun">' +
            '<p>Подготовка схемы расположения земельного участка на КПТ: {{ mejOpKv.price }} руб.</p><br>' +
        '</div>'+
        '<div v-if="orderTexPlan">' +
        '   <p>Подготовка технического плана здания: {{ mejOpKv.price }} руб.</p><br>' +
        '</div>'+
        '<div v-if="orderSoglOpr">' +
        '   <p>Составление соглашения об определении порядка пользования земельного участка: {{ mejOpKv.price }} руб.</p><br>' +
        '</div>'+
        '<b>Расчетная стоимость: {{ sum }}</b><br><hr>' +

        '<input type="checkbox" id="isSogl" v-model="isSogl">' +
        '<label for="isSogl">Я даю своё согласие на обработку моей персональной информации</label><br><hr>' +

        '<input type="button" value="Отправить" :disabled="!isSogl" @click="save" /><br>' +
        '<p>{{ ansver }}</p>' +
    '</div>',
    methods: {
        save: function () {
            var order = {familyName: this.familyName, firstName: this.firstName, patronymicName: this.patronymicName,
                dateBirth: this.dateBirth, email: this.email, phoneMobile: this.phoneMobile, phoneHome: this.phoneHome,
                phoneJob: this.phoneJob,  pasSerialNumber: this.pasSerial + this.pasNumber, pasDate: this.pasDate,
                pasIssued: this.pasIssued, snils: this.snils,
                addressResidence: this.addressResidence, addressRegistration: this.addressRegistration,
                orderMej: this.orderMej, orderVun: this.orderVun, mejOpKv: this.mejOpKv.id,
                addressObject: this.addressObject, typeProperty: this.typeProperty,
                orderTexPlan: this.orderTexPlan, orderSoglOpr: this.orderSoglOpr,
                entitlingType: this.entitlingType, entitlingNum: this.entitlingDate + " " + this.entitlingNum};

                 messageApi.save({}, order).then(result =>
                    result.json().then(data => {
                     this.ansver = data.req;
                    })
                )
        },
        sumOrd: function () {
            this.sum = 0;
            if(this.orderMej) {
                if(this.mejOpKv)
                    this.sum += this.mejOpKv.price;
            }
            if(this.orderVun) {
                if(this.mejOpKv)
                    this.sum += this.mejOpKv.price;
            }
            if(this.orderTexPlan) {
                if(this.mejOpKv)
                    this.sum += this.mejOpKv.price;
            }
            if(this.orderSoglOpr) {
                if(this.mejOpKv)
                    this.sum += this.mejOpKv.price;
            }

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