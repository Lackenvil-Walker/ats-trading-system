import { defineConfig } from 'vite';

export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 4200,
    allowedHosts: [
      'capital.alintatechsolutions.co.za',
      'localhost',
      '100.120.117.53',
      '.alintatechsolutions.co.za'  // Allow all subdomains
    ]
  }
});
