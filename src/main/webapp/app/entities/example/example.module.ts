import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MixwebSharedModule } from 'app/shared/shared.module';
import { ExampleComponent } from './example.component';
import { ExampleDetailComponent } from './example-detail.component';
import { ExampleUpdateComponent } from './example-update.component';
import { ExampleDeleteDialogComponent } from './example-delete-dialog.component';
import { exampleRoute } from './example.route';

@NgModule({
  imports: [MixwebSharedModule, RouterModule.forChild(exampleRoute)],
  declarations: [ExampleComponent, ExampleDetailComponent, ExampleUpdateComponent, ExampleDeleteDialogComponent],
  entryComponents: [ExampleDeleteDialogComponent]
})
export class MixwebExampleModule {}
