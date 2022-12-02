const BASE_URL = "http://localhost:8080/automovil";

const AutomovilManagerFrontApp = {
    data(){
        return {
            automoviles: [],
            nuevoAutomovil: {},
            automovilModificado: {},
            showAddForm: false,
            showUpdateForm: false,
            MostrarFormularioDeInicio: true,
            ListaDeVehiculos: false

        }
    }, 
    methods: {
        search: function() {

            this.MostrarFormularioDeInicio = false
            this.ListaDeVehiculos = true

            axios
                .get(BASE_URL + `/todos`)
                .then( response => {
                    this.automoviles = response.data
                    this.automoviles.forEach(auto => {
                        auto.active = false
                    });
                })
                .catch(e=> console.log(e))
        },
        toggleShowAddForm: function(){
            this.showAddForm = !this.showAddForm
            this.showUpdateForm = false
            this.MostrarFormularioDeInicio = false
        },
        toggleActive: function(item){
            item.active = !item.active;
        },
        agregarAutomovil: function(){

            axios
                .post(BASE_URL + "/agregarAutomovil", this.nuevoAutomovil)
                .then(response => {
                    console.log(response.data)
                })
                .catch(e=> console.log(e)) 
                this.showAddForm = !this.showAddForm                
        },
        borrarAutomovil: function(id){
            axios
                .delete(BASE_URL + `/borrarAutomovil/${id}`)
                .catch(e => console.log(e))
        }, 
        modificarAutomovil: function(auto){
            axios
            
                this.automovilModificado = auto;
                this.showUpdateForm = !this.showUpdateForm
                this.showAddForm = false
        },
        modificarbaseAutomovil: function(automovilModificado, id){
            axios
                .put(BASE_URL + `/modificarAutomovil/${id}`, this.automovilModificado, id)
                .catch(e => console.log(e))
                this.showUpdateForm = false
        }

    }
}
Vue.createApp(AutomovilManagerFrontApp).mount('#app');