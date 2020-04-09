import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

@Component({
  selector: 'jhi-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
  account!: Account;
  success = false;
  settingsForm = this.fb.group({
    name: [undefined, [Validators.required, Validators.maxLength(254)]],
    steamUrl: [undefined, []],
    email: [undefined, [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]]
  });

  constructor(private accountService: AccountService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => {
      if (account) {
        this.settingsForm.patchValue({
          name: account.name,
          steamUrl: account.steamUrl,
          email: account.email
        });

        this.account = account;
      }
    });
  }

  save(): void {
    this.success = false;

    this.account.name = this.settingsForm.get('name')!.value;
    this.account.steamUrl = this.settingsForm.get('steamUrl')!.value;
    this.account.email = this.settingsForm.get('email')!.value;

    this.accountService.save(this.account).subscribe(() => {
      this.success = true;

      this.accountService.authenticate(this.account);
    });
  }
}
