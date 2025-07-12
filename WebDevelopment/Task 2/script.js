// Scroll to Top Button
const scrollBtn = document.createElement("button");
scrollBtn.id = "scrollTopBtn";
scrollBtn.innerHTML = "↑";
document.body.appendChild(scrollBtn);

window.onscroll = () => {
  scrollBtn.style.display = document.documentElement.scrollTop > 100 ? "block" : "none";
};

scrollBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};

// Animate section headings on scroll
const animatedHeadings = document.querySelectorAll('.section-heading');

const observer = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add('animate-fade-slide');
    }
  });
}, {
  threshold: 0.5
});

animatedHeadings.forEach(heading => observer.observe(heading));

// Show role line animation on load
document.addEventListener("DOMContentLoaded", () => {
  const roleText = document.querySelector(".typing-text");
  roleText.classList.add("show-roles");
});
