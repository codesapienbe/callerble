document.addEventListener('DOMContentLoaded', function() {
  // Navigation and menu functionality
  initNavigation();
  
  // Copy to clipboard functionality
  initClipboard();
  
  // Scroll effects
  initScrollEffects();
});

// Initialize smooth scroll navigation and mobile menu
function initNavigation() {
  // Mobile menu toggle
  const menuToggle = document.querySelector('.menu-toggle');
  const navList = document.querySelector('.nav-list');
  
  if (menuToggle) {
    menuToggle.addEventListener('click', function() {
      this.classList.toggle('active');
      navList.classList.toggle('active');
    });
  }
  
  // Smooth scrolling for navigation links
  const navLinks = document.querySelectorAll('.nav-link, .hero-cta a[href^="#"], .footer-links a[href^="#"]');
  
  navLinks.forEach(link => {
    link.addEventListener('click', function(e) {
      // Only process internal links
      if (this.getAttribute('href').startsWith('#')) {
        e.preventDefault();
        
        // Close mobile menu if open
        if (menuToggle && menuToggle.classList.contains('active')) {
          menuToggle.classList.remove('active');
          navList.classList.remove('active');
        }
        
        const targetId = this.getAttribute('href');
        
        // Skip if it's just "#"
        if (targetId === '#') return;
        
        const targetElement = document.querySelector(targetId);
        if (targetElement) {
          // Calculate header height for offset
          const headerHeight = document.querySelector('.header').offsetHeight;
          const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY;
          
          window.scrollTo({
            top: targetPosition - headerHeight,
            behavior: 'smooth'
          });
        }
      }
    });
  });
}

// Initialize copy to clipboard functionality
function initClipboard() {
  const copyButtons = document.querySelectorAll('.copy-btn');
  
  copyButtons.forEach(button => {
    button.addEventListener('click', function() {
      const textToCopy = this.getAttribute('data-clipboard-text');
      
      // Create a temporary textarea element to copy from
      const textarea = document.createElement('textarea');
      textarea.value = textToCopy;
      textarea.setAttribute('readonly', '');
      textarea.style.position = 'absolute';
      textarea.style.left = '-9999px';
      document.body.appendChild(textarea);
      
      // Select and copy the text
      textarea.select();
      document.execCommand('copy');
      
      // Remove the temporary element
      document.body.removeChild(textarea);
      
      // Visual feedback
      const originalText = this.textContent;
      this.textContent = 'Copied!';
      this.classList.add('copied');
      
      // Reset button after a delay
      setTimeout(() => {
        this.textContent = originalText;
        this.classList.remove('copied');
      }, 2000);
    });
  });
}

// Initialize scroll effects
function initScrollEffects() {
  const header = document.querySelector('.header');
  const heroSection = document.querySelector('.hero');
  
  // Header scroll effect
  window.addEventListener('scroll', function() {
    if (window.scrollY > 50) {
      header.classList.add('scrolled');
    } else {
      header.classList.remove('scrolled');
    }
    
    // Initialize section animations
    revealSections();
  });
  
  // Reveal sections on scroll
  function revealSections() {
    const sections = document.querySelectorAll('section');
    const windowHeight = window.innerHeight;
    
    sections.forEach(section => {
      const sectionTop = section.getBoundingClientRect().top;
      if (sectionTop < windowHeight - 100) {
        section.classList.add('revealed');
      }
    });
  }
  
  // Call once on page load
  revealSections();
  
  // Highlight active section in navigation
  updateActiveNavLink();
  window.addEventListener('scroll', updateActiveNavLink);
  
  function updateActiveNavLink() {
    const sections = document.querySelectorAll('section[id]');
    const navLinks = document.querySelectorAll('.nav-link');
    
    // Determine which section is currently in view
    let currentSectionId = '';
    sections.forEach(section => {
      const sectionTop = section.offsetTop - 100;
      const sectionHeight = section.offsetHeight;
      const sectionId = section.getAttribute('id');
      
      if (window.scrollY >= sectionTop && window.scrollY < sectionTop + sectionHeight) {
        currentSectionId = sectionId;
      }
    });
    
    // Update active nav link
    navLinks.forEach(link => {
      link.classList.remove('active');
      if (link.getAttribute('href') === `#${currentSectionId}`) {
        link.classList.add('active');
      }
    });
  }
  
  // Initial highlight
  updateActiveNavLink();
}