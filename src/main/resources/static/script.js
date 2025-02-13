document.addEventListener("DOMContentLoaded", function () {
    // Alternar sidebar en móviles
    const sidebarToggle = document.querySelector("#sidebarToggle");
    const sidebar = document.querySelector(".sidebar");

    if (sidebarToggle) {
        sidebarToggle.addEventListener("click", function () {
            sidebar.classList.toggle("active");
        });
    }

    // Validación básica de formularios
    const forms = document.querySelectorAll("form");

    forms.forEach(form => {
        form.addEventListener("submit", function (event) {
            const inputs = form.querySelectorAll("input, textarea");
            let valid = true;

            inputs.forEach(input => {
                if (input.hasAttribute("required") && input.value.trim() === "") {
                    valid = false;
                    input.classList.add("error");
                } else {
                    input.classList.remove("error");
                }
            });

            if (!valid) {
                event.preventDefault();
                alert("Por favor, completa todos los campos requeridos.");
            }
        });
    });

    // Confirmación antes de eliminar publicaciones
    const deleteButtons = document.querySelectorAll(".btn-delete");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            const confirmDelete = confirm("¿Estás seguro de que deseas eliminar esta publicación?");
            if (!confirmDelete) {
                event.preventDefault();
            }
        });
    });
});
