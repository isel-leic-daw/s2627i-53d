import { parseTemplate } from 'url-template';

const emailUrlTemplate = parseTemplate('/{email}/{folder}/{id}');
let emailUrl: string = emailUrlTemplate.expand({
  email: 'user@domain',
  folder: 'test',
  id: 42
});


console.log(emailUrl);