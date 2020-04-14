import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MixwebSharedModule } from 'app/shared/shared.module';
import { MatchComponent } from './match.component';
import { MatchDetailComponent } from './match-detail.component';
import { MatchUpdateComponent } from './match-update.component';
import { MatchResultUpdateComponent } from './match-result-update.component';
import { MatchDeleteDialogComponent } from './match-delete-dialog.component';
import { matchRoute } from './match.route';

@NgModule({
  imports: [MixwebSharedModule, RouterModule.forChild(matchRoute)],
  declarations: [MatchComponent, MatchDetailComponent, MatchUpdateComponent, MatchResultUpdateComponent, MatchDeleteDialogComponent],
  entryComponents: [MatchDeleteDialogComponent]
})
export class MixwebMatchModule {}
