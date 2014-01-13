$().ready(function() {
    // Validates the user.jsp form
    $("#userForm").validate({
	rules: {
	    name: {
		required: true,
	    },
	    password: {
		required: true,
	    },
	},
	messages: {
	    name: {
		required: "Please enter a username",
	    },
	    password: {
		required: "Please provide a password",
	    }

	}
    });
    // Validates the editUser.jsp form
    $("#editForm").validate({
	rules: {
	    password: {
		required: true,
	    },
	},
	messages: {
	    password: {
		required: "Please provide a password",
	    }

	}
    });
});