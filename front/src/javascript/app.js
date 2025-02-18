document.addEventListener('DOMContentLoaded', function() {
    const ordenadoresLista = document.getElementById('ordenadores-lista');
    const ordenarPor = document.getElementById('ordenarPor');
    const formNuevoOrdenador = document.getElementById('form-nuevo-ordenador');
    const formEditarOrdenador = document.getElementById('form-editar-ordenador');
    const eliminarTodosBtn = document.getElementById('eliminarTodosBtn');
    
    function fetchOrdenadores() {
        const url = '/api/ordenadores';
        console.log('Fetching ordenadores from URL:', url);
        axios.get(url)
            .then(response => {
                const ordenadores = response.data;
                mostrarOrdenadores(ordenadores);
            })
            .catch(error => {
                console.error('Error fetching ordenadores:', error);
            });
    }

    function mostrarOrdenadores(ordenadores) {
        ordenadoresLista.innerHTML = '';
        ordenadores.forEach(ordenador => {
            const ordenadorDiv = document.createElement('div');
            ordenadorDiv.classList.add('col-md-4', 'producto-card');
            ordenadorDiv.innerHTML = `
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${ordenador.nombre}</h5>
                        <p class="card-text descripcion">
                            Color: 
                            <span style="background-color: ${ordenador.colorHex}; display: inline-block; width: 20px; height: 20px;"></span> 
                            ${ordenador.colorHex} 
                            <span style="color: ${ordenador.colorHex}; font-size: 1.5rem;">🖥️</span> <!-- Emoji color -->
                        </p>
                        <p class="card-text">Peso: ${ordenador.peso} kg</p>
                        <p class="card-text">Número de teclas: ${ordenador.numeroTeclas}</p>
                        <p class="card-text">Es Intel: ${ordenador.esIntel ? 'Sí' : 'No'}</p>
                        <button class="btn btn-secondary"  data-bs-toggle="modal" data-bs-target="#editarOrdenadorModal" onclick="cargarOrdenador(${ordenador.id})">Editar</button>
                        <button class="btn btn-danger"  onclick="eliminarOrdenador(${ordenador.id})">Eliminar</button>
                    </div>
                </div>
            `;
            ordenadoresLista.appendChild(ordenadorDiv);
        });
    }
    
    ordenarPor.addEventListener('change', function() {
        const criterio = ordenarPor.value;
        axios.get('/api/ordenadores')
            .then(response => {
                let ordenadores = response.data;
                ordenadores = ordenarOrdenadores(ordenadores, criterio);
                mostrarOrdenadores(ordenadores);
            })
            .catch(error => {
                console.error('Error fetching ordenadores:', error);
            });
    });

    function ordenarOrdenadores(ordenadores, criterio) {
        return ordenadores.sort((a, b) => {
            if (criterio === 'peso' || criterio === 'numeroTeclas') {
                return a[criterio] - b[criterio];
            } else if (criterio === 'esIntel') {
                return a[criterio] === b[criterio] ? 0 : a[criterio] ? -1 : 1;
            } else if (criterio === 'nombre' || criterio === 'colorHex') {
                return a[criterio].localeCompare(b[criterio]);
            } else {
                return a.id - b.id;
            }
        });
    }

    formNuevoOrdenador.addEventListener('submit', function(event) {
        event.preventDefault();
        const nuevoOrdenador = {
            nombre: document.getElementById('nuevo-nombre').value,
            colorHex: document.getElementById('nuevo-color').value,
            peso: parseFloat(document.getElementById('nuevo-peso').value),
            numeroTeclas: parseInt(document.getElementById('nuevo-numeroTeclas').value),
            esIntel: document.getElementById('nuevo-esIntel').value === 'true'
        };
        axios.post('/api/ordenadores', nuevoOrdenador)
            .then(response => {
                fetchOrdenadores();
                formNuevoOrdenador.reset();
                bootstrap.Modal.getInstance(document.getElementById('nuevoOrdenadorModal')).hide();
            })
            .catch(error => {
                console.error('Error adding ordenador:', error);
            });
    });

    formEditarOrdenador.addEventListener('submit', function(event) {
        event.preventDefault();
        const id = document.getElementById('editar-id').value;
        const editarOrdenador = {
            nombre: document.getElementById('editar-nombre').value,
            colorHex: document.getElementById('editar-color').value,
            peso: parseFloat(document.getElementById('editar-peso').value),
            numeroTeclas: parseInt(document.getElementById('editar-numeroTeclas').value),
            esIntel: document.getElementById('editar-esIntel').value === 'true'
        };
        axios.put(`/api/ordenadores/${id}`, editarOrdenador)
            .then(response => {
                fetchOrdenadores();
                formEditarOrdenador.reset();
                bootstrap.Modal.getInstance(document.getElementById('editarOrdenadorModal')).hide();
            })
            .catch(error => {
                console.error('Error updating ordenador:', error);
            });
    });

    window.cargarOrdenador = function(id) {
        axios.get(`/api/ordenadores/${id}`)
            .then(response => {
                const ordenador = response.data;
                document.getElementById('editar-id').value = ordenador.id;
                document.getElementById('editar-nombre').value = ordenador.nombre;
                document.getElementById('editar-color').value = ordenador.colorHex;
                document.getElementById('editar-peso').value = ordenador.peso;
                document.getElementById('editar-numeroTeclas').value = ordenador.numeroTeclas;
                document.getElementById('editar-esIntel').value = ordenador.esIntel.toString();
            })
            .catch(error => {
                console.error('Error fetching ordenador:', error);
            });
    };

    window.eliminarOrdenador = function(id) {
        const eliminarBtn = document.getElementById('eliminarConfirmado');
        const confirmarCheck = document.getElementById('confirmarCheck');
        const modal = new bootstrap.Modal(document.getElementById('confirmarEliminarModal')); // Mostrar el modal de confirmación
        eliminarBtn.disabled = true;
        // Habilitar el botón de eliminar solo si se marca el checkbox
        confirmarCheck.addEventListener('change', function() {
            eliminarBtn.disabled = !confirmarCheck.checked;
        });
        
        // Mostrar el modal
        modal.show();
        
        // Acción de eliminar confirmada
        eliminarBtn.onclick = function() {
            axios.delete(`/api/ordenadores/${id}`)
                .then(response => {
                    fetchOrdenadores(); // Refrescar la lista
                    modal.hide(); // Cerrar el modal
                })
                .catch(error => {
                    console.error('Error deleting ordenador:', error);
                    modal.hide(); // Cerrar el modal si hay error
                });
        };
    };

    eliminarTodosBtn.addEventListener('click', function() {
        const eliminarBtn = document.getElementById('eliminarConfirmado2');
        const confirmarCheck = document.getElementById('confirmarCheck2');
        const modal = new bootstrap.Modal(document.getElementById('confirmarEliminarModal2')); // Mostrar el modal de confirmación
        eliminarBtn.disabled = true;
        // Habilitar el botón de eliminar solo si se marca el checkbox
        confirmarCheck.addEventListener('change', function() {
            eliminarBtn.disabled = !confirmarCheck.checked;
        });
        modal.show();
        // Mostrar el modal
        eliminarBtn.onclick = function() {
            axios.delete('/api/ordenadores')
                .then(response => {
                    fetchOrdenadores();
                    modal.hide();
                })
                .catch(error => {
                    console.error('Error deleting all ordenadores:', error);
                    modal.hide();
                });
        }
    });
    
    fetchOrdenadores();
});