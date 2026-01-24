// import { Component, inject } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterModule } from '@angular/router';
// import { AuthService } from '../../services/auth.service';

// @Component({
//   selector: 'app-navbar',
//   standalone: true,
//   imports: [CommonModule, RouterModule],
//   templateUrl: './navbar.html',
//   styleUrl: './navbar.css'
// })
// export class Navbar {
//   authService = inject(AuthService);

//   logout() {
//     this.authService.logout();
//   }
// }

import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { LogoutConfirmationComponent } from '../logout-confirmation/logout-confirmation';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule, LogoutConfirmationComponent],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
})
export class Navbar {
  constructor(private router: Router, private toastr: ToastrService) {}
  authService = inject(AuthService);
  menuOpen = false;
  showLogoutModal = signal(false);

  openLogoutModal() {
    this.showLogoutModal.set(true);
  }

  handleLogoutConfirmation(confirmed: boolean) {
    this.showLogoutModal.set(false);
    
    if (confirmed) {
      this.performLogout();
    }
  }

  private performLogout() {
    this.authService.logout();
    
    // Show success toast with custom styling
    this.toastr.success(
      'تم تسجيل الخروج بنجاح',
      'وداعاً',
      {
        positionClass: 'toast-bottom-left',
        timeOut: 2000,
        progressBar: true,
        closeButton: false,
        easing: 'ease-in-out'
      }
    );
    
    this.menuOpen = false;
    
    // Reload after a brief delay to allow toast to be visible
    setTimeout(() => {
      window.location.reload();
    }, 800);
  }
}

