/**
 * Represents an item in the shopping cart
 */
import Product from '@/domain/Product'

export class CartItem {

    private readonly _product: Product

    private _quantity: number

    private _total: number

    constructor(product: Product, quantity: number) {
        this._product = product
        this._quantity = quantity
        this._total = product.price * quantity
    }

    public get thumbnail(): string {
        return this._product.thumbnailImage.url
    }

    public get product(): Product {
        return this._product
    }

    public get quantity(): number {
        return this._quantity
    }

    public set quantity(value: number) {
        this._quantity = value
        this._total = this._product.price * this._quantity
    }

    public addQuantity(value: number){
        this._quantity += value
        this._total = this._product.price * this._quantity
    }

    public removeQuantity(value: number){
        this._quantity -= value
        this._total = this._product.price * this._quantity
    }

    public get total(): number {
        return this._total
    }
}