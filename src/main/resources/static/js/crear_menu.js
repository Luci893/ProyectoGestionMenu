
let contador = 0;

// Estructura global de menu
let menu = {
    miGerente: null,
    alimentos: []
};

function generarAlimentos() {

    const cantidad = parseInt(document.getElementById("cantidadAlimentos").value);
    const contenedor = document.getElementById("contenedorAlimentos");

    contenedor.innerHTML = "";
    // Limpiar modales anteriores
    document.querySelectorAll(".modal-dinamico").forEach(m => m.remove());

    contador = 0;
    menu.alimentos = [];

    if (isNaN(cantidad) || cantidad < 1) {
        alert("Ingrese una cantidad válida");
        return;
    }

    if (cantidad > 6) {
        alert("El máximo permitido de alimentos son 6.");
        return;
    }

    for (let i = 1; i <= cantidad; i++) {
        agregarAlimento(i);
    }
}

function agregarAlimento(numero) {

    contador = numero;

    const contenedor = document.getElementById("contenedorAlimentos");

    const div = document.createElement("div");
    div.className = "col-md-6 mb-4 alimento-item";

    div.innerHTML = `
        <div class="crear-menu-card card p-3">

            <h5>Alimento ${numero}</h5>

            <input type="text" class="crear-menu-input form-control mb-2 nombre-alimento" placeholder="Nombre alimento">
            <input type="number" class="crear-menu-input form-control mb-2 precio-alimento" placeholder="Precio">

            <button class="crear-menu-btn btn btn-primary btn-crear-menu w-100"
                    data-bs-toggle="modal"
                    data-bs-target="#modalReceta${numero}">
                Agregar Receta
            </button>

        </div>
    `;

    contenedor.appendChild(div);

    crearModal(numero);

    // Agregar estructura vacía al menu
     menu.alimentos.push({
        nombre: "",
        precio: "",
        receta: null
    });
}

// Agregar la cantidad de ingredientes en base a la cantidad colocada en "Cantidad de ingredientes"
function generarIngredientes(btn) {

    const modal = btn.closest(".modal");
    const cantidad = parseInt(modal.querySelector(".cantidad-ingredientes").value);
    const container = modal.querySelector(".ingredientes-container");

    container.innerHTML = "";

    if (isNaN(cantidad) || cantidad < 1) {
        alert("Ingrese una cantidad válida de ingredientes");
        return;
    }

    for (let i = 0; i < cantidad; i++) {

        const input = document.createElement("input");
        input.className = "form-control mb-2 ingrediente";
        input.placeholder = "Ingrediente " + (i + 1);

        container.appendChild(input);
    }
}

// Modal Receta
function crearModal(id) {

    const modal = document.createElement("div");
    modal.className = "modal-dinamico"; // clase para identificar y limpiar modales dinámicos

    modal.innerHTML = `
    <div class="modal fade" id="modalReceta${id}" tabindex="-1">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">

          <div class="modal-header">
            <h5 class="modal-title">Receta Alimento ${id}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <div class="modal-body">

            <input type="text" class="form-control mb-2 nombre-receta" placeholder="Nombre receta">
            <textarea class="form-control mb-2 descripcion-receta" placeholder="Descripción"></textarea>

            <hr>

            <h6>Chef</h6>
            <input type="text" class="form-control mb-2 chef-nombre" placeholder="Nombre">
            <input type="text" class="form-control mb-2 chef-cedula" placeholder="Cédula">
            <input type="text" class="form-control mb-2 chef-telefono" placeholder="Teléfono">
            <input type="email" class="form-control mb-2 chef-correo" placeholder="Correo">
            <input type="date" class="form-control mb-2 chef-fecha">
            <input type="number" class="form-control mb-2 chef-salario" placeholder="Salario">

            <hr>

            <h6>Ingredientes</h6>
            <input type="number" class="form-control mb-2 cantidad-ingredientes" placeholder="Cantidad de ingredientes">
            <button type="button" class="crear-menu-btn btn btn-secondary w-100 mb-2" onclick="generarIngredientes(this)">Generar ingredientes</button>

            <div class="ingredientes-container"></div>

            <button type="button" class="btn btn-success w-100 mt-3" onclick="guardarReceta(${id}, this)">Guardar Receta</button>

          </div>

        </div>
      </div>
    </div>
    `;

    document.body.appendChild(modal);
}

function validarReceta(modal) {

    // CAMPOS OBLIGATORIOS (receta + chef básico)
    const campos = modal.querySelectorAll(
        ".nombre-receta, .descripcion-receta, .chef-nombre, .chef-cedula, .chef-telefono, .chef-correo, .chef-fecha, .chef-salario"
    );

    for (let campo of campos) {
        if (!campo.value || !campo.value.trim()) {
            return "Hay campos vacíos en la receta o chef";
        }
    }

    const cedula = modal.querySelector(".chef-cedula").value;

    if (cedula.length !== 10) {
        return "La cédula debe tener exactamente 10 caracteres";
    }

    // VALIDAR CORREO
    const correo = modal.querySelector(".chef-correo").value;
    if (!correo.includes("@")) {
        return "Correo inválido";
    }

    // VALIDAR TELÉFONO (solo números)
    const telefono = modal.querySelector(".chef-telefono").value;
    if (!/^[0-9]+$/.test(telefono)) {
        return "El teléfono debe contener solo números";
    }

    // VALIDAR SALARIO (positivo)
    const salario = parseFloat(modal.querySelector(".chef-salario").value);
    if (isNaN(salario) || salario <= 0) {
        return "El salario debe ser un número positivo";
    }

    // VALIDAR FECHA (presente o anterior)
    const fechaInput = modal.querySelector(".chef-fecha").value;
    const fechaIngresada = new Date(fechaInput);
    const hoy = new Date();

    // quitar horas para comparar solo fechas
    hoy.setHours(0,0,0,0);

    if (!fechaInput || fechaIngresada > hoy) {
        return "La fecha no puede ser futura";
    }

    // INGREDIENTES
    const ingredientes = modal.querySelectorAll(".ingrediente");
    if (ingredientes.length === 0) {
        return "Debe agregar ingredientes";
    }

    for (let ing of ingredientes) {
        if (!ing.value.trim()) {
            return "Hay ingredientes vacíos";
        }
    }

    return null; 
}

function guardarReceta(id, btn) {

    // Busca el modal donde se hizo clic en "Guardar Receta"
    const modal = btn.closest(".modal");

    // Validar datos antes de guardar
    const error = validarReceta(modal);
    if(error){
        alert(error);
        return;
    }

    // Crea el objeto receta con los datos ingresados en el formulario
    const receta = {
        
        nombreReceta: modal.querySelector(".nombre-receta").value,
        descripcionProceso: modal.querySelector(".descripcion-receta").value,

        // Objeto chef dentro de la receta
        chef: {
            nombre: modal.querySelector(".chef-nombre").value,
            cedula: modal.querySelector(".chef-cedula").value,
            telefono: modal.querySelector(".chef-telefono").value,
            correo: modal.querySelector(".chef-correo").value,
            fechaVinculacion: modal.querySelector(".chef-fecha").value,
            salario: modal.querySelector(".chef-salario").value
        },

        // Lista donde se guardarán los ingredientes
        ingredientes: []
    };

    // Selecciona todos los inputs de ingredientes creados dinámicamente
    const ingredientesInputs = modal.querySelectorAll(".ingrediente");

    // Recorre cada input de ingrediente y guarda su valor en el array
    ingredientesInputs.forEach(i => {
        receta.ingredientes.push(i.value);
    });

    // Guardar receta en el alimento correspondiente
    menu.alimentos[id - 1].receta = receta;
    alert("Receta guardada correctamente");

    // Cierra el modal automáticamente después de guardar
    bootstrap.Modal.getInstance(modal).hide();
}

// VALIDAR GERENTE ANTES DE ENVIAR
function validarGerente() {

    const nombre = document.getElementById("gerenteNombre").value;
    const cedula = document.getElementById("gerenteCedula").value;
    const telefono = document.getElementById("gerenteTelefono").value;
    const correo = document.getElementById("gerenteCorreo").value;

    if (!nombre || !cedula || !telefono || !correo)
        return "Completar datos del gerente";

    if (cedula.length !== 10) {
        return "La cédula del gerente debe tener exactamente 10 caracteres";
    }

    if (!/^[a-zA-Z\s]+$/.test(nombre))
        return "Nombre del gerente inválido";

    if (!/^[0-9]+$/.test(telefono))
        return "Teléfono del gerente inválido";

    if (!correo.includes("@"))
        return "Correo del gerente inválido";

    return null;
}

// VALIDAR ALIMENTOS
function validarAlimentos() {

    const items = document.querySelectorAll(".alimento-item");

    for (let i = 0; i < items.length; i++) {

        const nombre = items[i].querySelector(".nombre-alimento").value;
        const precio = items[i].querySelector(".precio-alimento").value;

        if (!nombre || !precio)
            return `Faltan datos en alimento ${i + 1}`;

        if (!/^[a-zA-Z\s]+$/.test(nombre))
            return `Nombre inválido en alimento ${i + 1}`;

        if (isNaN(precio) || Number(precio) <= 0)
            return `Precio inválido en alimento ${i + 1}`;

        if (menu.alimentos[i].receta === null)
            return `El alimento ${i + 1} no tiene receta guardada`;
    }

    return null;
}

// VALIDACIÓN FINAL
function validarMenuCompleto() {

    let error;

    error = validarGerente();
    if (error) return error;

    error = validarAlimentos();
    if (error) return error;

    return null;
}

// GUARDAR MENÚ COMPLETO
let botonGuardar = document.getElementById("btnGuardar");

botonGuardar.addEventListener('click', evento=>{
    registrarMenu();
})

let registrarMenu = async()=>{

    let error = validarMenuCompleto();

    if (error) {
        alert(error);
        return;
    }

    // Obtener los datos para mandarlos 

    // Guardar datos ingresados del gerente en el menu
    menu.miGerente = {
        nombre: document.getElementById("gerenteNombre").value.trim(),
        cedula: document.getElementById("gerenteCedula").value.trim(),
        telefono: document.getElementById("gerenteTelefono").value.trim(),
        correo: document.getElementById("gerenteCorreo").value.trim()
    };

    // Guardar datos de cada alimento (nombre y precio)
    document.querySelectorAll(".alimento-item").forEach((item, i) => {
        menu.alimentos[i].nombre = item.querySelector(".nombre-alimento").value.trim();
        menu.alimentos[i].precio = parseFloat(item.querySelector(".precio-alimento").value);
    });


    try {
        const respuesta = await fetch("http://localhost:9000/api/menus",
        {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(menu) // envia los datos serializados
        });

        // Mensaje según resultado:
        if (respuesta.ok){
            alert("Menú guardado correctamente")
            // Resetear formulario
            location.reload();
        } else{
            // Respuesta detallada del error
            const data = await respuesta.json();
            console.error("Error backend:", data);
            alert("Error al guardar el menú: " + JSON.stringify(data));
        }

    } catch (error) {
        console.error(error);
        alert("No se pudo oconectar con el servidor");
    }
    
}