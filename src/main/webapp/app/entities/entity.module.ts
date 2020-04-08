import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'example',
        loadChildren: () => import('./example/example.module').then(m => m.MixwebExampleModule)
      },
      {
        path: 'match',
        loadChildren: () => import('./match/match.module').then(m => m.MixwebMatchModule)
      }
    ])
  ]
})
export class MixwebEntityModule {}
