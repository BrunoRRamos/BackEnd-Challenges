package com.desafios_backend.criptography;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CardService {

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    public CardDto createCard(CardDto cardDto) throws Exception {
        CardModel cardModel = this.modelMapper.map(cardDto, CardModel.class);
        cardModel.setCreditCardToken(RSAUtils.encrypt(cardModel.getCreditCardToken(), this.publicKey));
        cardModel.setUserDocument(RSAUtils.encrypt(cardModel.getUserDocument(), this.publicKey));
        CardModel savedCard = this.cardRepository.save(cardModel);

        return this.modelMapper.map(savedCard, CardDto.class);
    }

    public Collection<CardDto> getAllCards() throws Exception{
        Collection<CardModel> cardModelCollection = this.cardRepository.findAll();
        Collection<CardModel> cardModelCollectionDecrypted = this.decryptMany(cardModelCollection);

        return cardModelCollectionDecrypted
                .stream()
                .map(cardModel -> this.modelMapper.map(cardModel, CardDto.class))
                .collect(Collectors.toList());
    }

    private Collection<CardModel> decryptMany(Collection<CardModel> cards) throws Exception {

        return cards.stream().map(cardModel -> {
            try {
                cardModel.setCreditCardToken(RSAUtils.decrypt(cardModel.getCreditCardToken(), this.privateKey));
                cardModel.setUserDocument(RSAUtils.decrypt(cardModel.getUserDocument(), this.privateKey));
                return cardModel;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao descriptografar cartão", e);
            }
        }).toList();
    }
}
