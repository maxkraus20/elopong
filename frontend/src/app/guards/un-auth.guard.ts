import { CanActivateFn } from '@angular/router';

export const UnAuthGuard: CanActivateFn = (route, state) => {
  return true;
};
