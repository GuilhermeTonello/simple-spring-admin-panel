let menuButtonField = document.getElementById('menu-button-field');
let menuButton = document.getElementById('menu-button');
let expandedNavbar = false;
let expandableTexts = document.getElementsByClassName('navbar-expandable-text');

menuButtonField.addEventListener('click', () => {
	if (expandedNavbar) {
		expandedNavbar = false;
		menuButton.innerText = 'menu';
		menuButtonField.title = 'Click to expand menu';
		for (let i = 0; i < expandableTexts.length; i++) {
			expandableTexts[i].classList.add('hide');
		}
	} else {
		expandedNavbar = true;
		menuButton.innerText = 'close';
		menuButtonField.title = 'Click to compress menu';
		for (let i = 0; i < expandableTexts.length; i++) {
			expandableTexts[i].classList.remove('hide');
		}
	}
});

let profileMenuButton = document.getElementById('account-button-open');
let profileMenuItems = document.getElementById('profile-items');
let expandedProfileMenu = false;

profileMenuButton.addEventListener('click', () => {
	if (expandedProfileMenu) {
		expandedProfileMenu = false;
		profileMenuItems.classList.add('hide');
	} else {
		expandedProfileMenu = true;
		profileMenuItems.classList.remove('hide');
	}
});
