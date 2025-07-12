// Change navbar background on scroll
window.addEventListener("scroll", () => {
  const header = document.getElementById("navbar");
  header.classList.toggle("scrolled", window.scrollY > 50);
});
// Navbar scroll background
window.addEventListener("scroll", () => {
  document.getElementById("navbar").classList.toggle("scrolled", window.scrollY > 50);
});

// Testimonial slider
const testimonials = [
  { text: `"AK's TECH transformed our idea into a full-fledged solution. Highly recommended!"`, author: "- Sarah, Startup Founder" },
  { text: `"Amazing UI and super fast development!"`, author: "- Ravi, Entrepreneur" },
  { text: `"Reliable and passionate team. Delivered on time!"`, author: "- Maya, Project Manager" }
];

let currentTestimonial = 0;
setInterval(() => {
  currentTestimonial = (currentTestimonial + 1) % testimonials.length;
  document.getElementById("testimonial-text").textContent = testimonials[currentTestimonial].text;
  document.getElementById("testimonial-author").textContent = testimonials[currentTestimonial].author;
}, 4000);

// Scroll to top button
const scrollBtn = document.getElementById("scrollTopBtn");
window.addEventListener("scroll", () => {
  scrollBtn.style.display = window.scrollY > 300 ? "block" : "none";
});
scrollBtn.addEventListener("click", () => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

// Dark mode toggle
document.getElementById("modeSwitch").addEventListener("change", (e) => {
  document.body.classList.toggle("dark-mode", e.target.checked);
});
