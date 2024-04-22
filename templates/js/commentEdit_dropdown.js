document.addEventListener('DOMContentLoaded', function() {
    var editButtons = document.querySelectorAll('.chatEdit');

    editButtons.forEach(function(btn) {
        btn.addEventListener('click', function() {
            var dropdownMenu = this.parentNode.querySelector('.dropdown-menu');
            if (dropdownMenu) {
                dropdownMenu.classList.toggle('show');
            }
        });
    });


      
});


