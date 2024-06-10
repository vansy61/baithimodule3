document.querySelectorAll(".btn-destroy").forEach(el => {
    el.addEventListener("click", function (e) {
        //     confirm before delete
        if (!confirm("Bạn có muốn xóa không?")) {
            e.preventDefault()
        }
    })
})
