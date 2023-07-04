const alertPlaceholder = document.getElementById('liveAlertPlaceholder');
 const appendAlert = (msg, type) => {
  const alertDiv = document.createElement('div');
  alertDiv.classList.add('alert', `alert-${type}`, 'alert-dismissible', 'd-flex', 'align-items-center');
  alertDiv.setAttribute('role', 'alert');
   const iconSvg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
  iconSvg.classList.add('bi', 'flex-shrink-0', 'me-2');
  iconSvg.setAttribute('role', 'img');
  iconSvg.setAttribute('aria-label', 'Success:');
   const iconPath = document.createElementNS('http://www.w3.org/2000/svg', 'use');
  iconPath.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', '#check-circle-fill');
  iconSvg.appendChild(iconPath);
   const messageDiv = document.createElement('div');
  messageDiv.textContent = msg;
   const closeButton = document.createElement('button');
  closeButton.setAttribute('type', 'button');
  closeButton.classList.add('btn-close');
  closeButton.setAttribute('data-bs-dismiss', 'alert');
  closeButton.setAttribute('aria-label', 'Close');
   alertDiv.appendChild(iconSvg);
  alertDiv.appendChild(messageDiv);
  alertDiv.appendChild(closeButton);
   alertPlaceholder.appendChild(alertDiv);
};
 const message = "[[${message}]]";
 if (message) {
    alert(message);
  appendAlert(message, 'success');
}