
// Al cargar la página, trae los menús y los muestra
async function cargarMenus() {

    const respuesta = await fetch("http://localhost:9000/api/menus");
    const menus = await respuesta.json();

    const cuerpo = document.getElementById("cuerpoTabla");

    menus.forEach(menu => {

        // Junta los nombres de alimentos en un solo texto
        const alimentos = menu.alimentos.map(a => a.nombre).join(", ");

        // Junta los nombres de recetas en un solo texto
        const recetas = menu.alimentos.map(a => a.receta.nombreReceta).join(", ");

        // Junta los nombres de chefs en un solo texto
        const chefs = menu.alimentos.map(a => a.receta.chef.nombre).join(", ");

        // Crea la fila con todos los datos
        cuerpo.innerHTML += `
            <tr>
                <td>${menu.idMenu}</td>
                <td>${menu.miGerente.nombre}</td>
                <td>${alimentos}</td>
                <td>${recetas}</td>
                <td>${chefs}</td>
                <td>
                    <button class="btn btn-danger btn-sm"
                            onclick="eliminarMenu(${menu.idMenu})">
                        Eliminar
                    </button>
                </td>
            </tr>
        `;
    });
}

// Elimina un menú y recarga la tabla
async function eliminarMenu(id) {

    const confirmar = confirm("¿Seguro que deseas eliminar este menú?");

    if (confirmar == false) {
        return;
    }

    await fetch("http://localhost:9000/api/menus/" + id, 
        { 
            method: "DELETE"
        });

    // Limpia la tabla y la vuelve a cargar
    document.getElementById("cuerpoTabla").innerHTML = "";
    cargarMenus();
}

cargarMenus();