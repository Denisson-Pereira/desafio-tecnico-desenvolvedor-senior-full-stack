describe('Página de Login', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('deve logar com email e senha válidos e redirecionar para /home', () => {
    cy.get('#email')
      .type('user@teste.com')
      .should('have.value', 'user@teste.com');

    cy.get('#senha')
      .type('123456')
      .should('have.value', '123456');

    cy.get('form').submit();

    cy.url().should('include', '/home');

    cy.contains('Boletim').should('be.visible');
  });
});
