import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  private router: Router;

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
<<<<<<< HEAD
    if (localStorage.getItem('token') != null) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
=======
      if(localStorage.getItem('token') != null) {
    		return true;
    	} else {
    		this.router.navigate(['/login']);
    		return false;
      }
>>>>>>> 1b022eb2ec59bd63b533b8805d7c774f57dbd6ca
  }
}
